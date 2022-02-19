package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.CountryDao;
import com.oleh.chui.model.entity.Country;
import com.oleh.chui.model.exception.city.CityNotExistException;
import com.oleh.chui.model.exception.country.CountryNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CountryService {

    private final Logger logger = LogManager.getLogger(CountryService.class);

    private final CountryDao countryDao;

    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void checkCountryAndCityExist(String country, String city) throws CountryNotExistException, CityNotExistException {
        Optional<Country> countryOptional = countryDao.findByName(country);

        if (countryOptional.isPresent()) {
            boolean cityNotExists = countryOptional.get().getCityList()
                    .stream()
                    .noneMatch(cityObj -> cityObj.getCity().equals(city));

            if (cityNotExists) {
                logger.warn("city ({}) doesn't exist", city);
                throw new CityNotExistException();
            }
        } else {
            logger.warn("country ({}) doesn't exist", country);
            throw new CountryNotExistException();
        }
    }

}
