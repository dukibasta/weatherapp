package com.example.weatherapp.Activities;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.Adapters.DaysAdapter.DailyAdapter;
import com.example.weatherapp.Adapters.HourlyAdapter.HourlyAdapter;
import com.example.weatherapp.ErrorHandling.ErrorHandling;
import com.example.weatherapp.GPSProvider.LocationCallback.LocationProviderCallback;
import com.example.weatherapp.ErrorHandling.CustomError;
import com.example.weatherapp.Models.DailyResponse;
import com.example.weatherapp.Models.FilterModel;
import com.example.weatherapp.Models.GPSCoordinates;
import com.example.weatherapp.Models.HourlyResponse;
import com.example.weatherapp.Models.WeatherResponse;
import com.example.weatherapp.R;
import com.example.weatherapp.Services.CallbackService.IApiResponseCallback;
import com.example.weatherapp.Services.WeatherApiService;
import com.example.weatherapp.GPSProvider.LocationProviderUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nsvMainContainer)
    NestedScrollView nsvMainContainer;

    @BindView(R.id.tvInfo)
    TextView tvInfo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvHourly)
    RecyclerView rvHourly;

    @BindView(R.id.hourlyForecastValidation)
    TextView hourlyForecastValidation;

    @BindView(R.id.rvDays)
    RecyclerView rvDaily;

    @BindView(R.id.dailyForecastValidation)
    TextView dailyForecastValidation;

    @BindView(R.id.tvCurrentTemperature)
    TextView tvCurrentTemperature;

    @BindView(R.id.tvMinMaxTemperature)
    TextView tvMinMaxTemperature;

    @BindView(R.id.tvMainWeatherDescription)
    TextView tvMainWeatherDescription;

    @BindView(R.id.tvCityName)
    TextView tvCityName;

    private HourlyAdapter hourlyAdapter;
    private DailyAdapter dailyAdapter;
    private FilterModel filterModel = new FilterModel();
    private SearchView searchViewCity;
    private MenuItem menuItemSearchCity;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        load();
    }

    private void setup() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        activity = this;

        hourlyAdapter = new HourlyAdapter();
        rvHourly.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvHourly.setAdapter(hourlyAdapter);

        dailyAdapter = new DailyAdapter();
        rvDaily.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvDaily.setAdapter(dailyAdapter);
    }

    private void load(){
        if(LocationProviderUtilities.checkGPSStatus(this)){
            startProgressBar();
            LocationProviderUtilities.requestSingleUpdate(this, new LocationProviderCallback() {
                @Override
                public void onNewLocationAvailable(GPSCoordinates location) {
                    filterModel.coordinates = location;
                    getWeatherByLocation();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        menuItemSearchCity = (MenuItem) menu.findItem(R.id.searchViewCity);
        searchViewCity = (SearchView) menuItemSearchCity.getActionView();
        searchViewCity.setQueryHint(this.getString(R.string.main_activity_search_by_city_name));

        searchViewCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterModel.cityName = query;
                getWeatherByCityName();
                searchViewCity.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void getWeatherByCityName() {
        startProgressBar();
        WeatherApiService.getWeatherByCityName(filterModel, new IApiResponseCallback<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse object) {
                tvInfo.setVisibility(View.GONE);
                nsvMainContainer.setVisibility(View.VISIBLE);
                populateCurrentWeather(object);
                getForecastHourlyByCityName();
            }

            @Override
            public void onError(CustomError error) {
                getForecastHourlyByCityName();
                ErrorHandling.handlingError(activity, error, null);
            }
        });
    }

    private void getForecastHourlyByCityName(){
        WeatherApiService.getForecastHourlyByCityName(filterModel, new IApiResponseCallback<HourlyResponse>() {
            @Override
            public void onSuccess(HourlyResponse object) {
                populateForecastHourly(object);
                getForecastDailyByCityName();
            }

            @Override
            public void onError(CustomError error) {
                rvHourly.setVisibility(View.GONE);
                getForecastDailyByCityName();
                ErrorHandling.handlingError(activity, error, hourlyForecastValidation);
            }
        });
    }

    private void getForecastDailyByCityName(){
        WeatherApiService.getForecastDailyByCityName(filterModel, new IApiResponseCallback<DailyResponse>() {
            @Override
            public void onSuccess(DailyResponse object) {
                endProgressBar();
                populateForecastDaily(object);
            }

            @Override
            public void onError(CustomError error) {
                endProgressBar();
                rvDaily.setVisibility(View.GONE);
                ErrorHandling.handlingError(activity, error, dailyForecastValidation);
            }
        });
    }

    private void getWeatherByLocation() {
        startProgressBar();
        WeatherApiService.getWeatherByLocation(filterModel, new IApiResponseCallback<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse object) {
                tvInfo.setVisibility(View.GONE);
                nsvMainContainer.setVisibility(View.VISIBLE);
                if (menuItemSearchCity != null){
                    menuItemSearchCity.expandActionView();
                }
                if (searchViewCity != null){
                    searchViewCity.clearFocus();
                    searchViewCity.setQuery(object.name, false);
                }
                populateCurrentWeather(object);
                getForecastHourlyByLocation();
            }

            @Override
            public void onError(CustomError error) {
                getForecastHourlyByLocation();
                ErrorHandling.handlingError(activity, error, null);
            }
        });
    }

    private void getForecastHourlyByLocation(){
        WeatherApiService.getForecastHourlyByLocation(filterModel, new IApiResponseCallback<HourlyResponse>() {
            @Override
            public void onSuccess(HourlyResponse object) {
                populateForecastHourly(object);
                getForecastDailyByLocation();
            }

            @Override
            public void onError(CustomError error) {
                rvHourly.setVisibility(View.GONE);
                getForecastDailyByLocation();
                ErrorHandling.handlingError(activity, error, hourlyForecastValidation);
            }
        });
    }

    private void getForecastDailyByLocation(){
        WeatherApiService.getForecastDailyByLocation(filterModel, new IApiResponseCallback<DailyResponse>() {
            @Override
            public void onSuccess(DailyResponse object) {
                endProgressBar();
                populateForecastDaily(object);
            }

            @Override
            public void onError(CustomError error) {
                endProgressBar();
                rvDaily.setVisibility(View.GONE);
                ErrorHandling.handlingError(activity, error, dailyForecastValidation);
            }
        });
    }

    private void populateCurrentWeather(WeatherResponse response){
        tvCurrentTemperature.setText(response.main.getCurrentTemperatureFormated());
        tvMinMaxTemperature.setText(response.main.getMinMaxTemperatureFormated());
        tvMainWeatherDescription.setText(response.getMainWeatherDescription());
        tvCityName.setText(response.name);
    }

    private void populateForecastHourly(HourlyResponse response){
        rvHourly.setVisibility(View.VISIBLE);
        hourlyForecastValidation.setVisibility(View.GONE);
        hourlyAdapter.addHourlies(response.hourlies);
    }

    private void populateForecastDaily(DailyResponse response){
        rvDaily.setVisibility(View.VISIBLE);
        dailyForecastValidation.setVisibility(View.GONE);
        dailyAdapter.addDailys(response.dailies);
    }
}
