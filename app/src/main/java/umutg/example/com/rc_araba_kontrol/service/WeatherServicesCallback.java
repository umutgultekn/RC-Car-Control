package umutg.example.com.rc_araba_kontrol.service;

import umutg.example.com.rc_araba_kontrol.data.Channel;

public interface WeatherServicesCallback {
    void servicesSuccess(Channel channel);

    void servicesFailure(Exception exception);
}
