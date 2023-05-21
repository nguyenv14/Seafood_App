package com.nhanlovecode.doancuoiky.Views.DressPersonal.AddDressPersonal;

import com.nhanlovecode.doancuoiky.Models.City;
import com.nhanlovecode.doancuoiky.Models.Province;
import com.nhanlovecode.doancuoiky.Models.Wards;

import java.util.List;

public interface AddDressPersonalMVPView {
    void getDataCitySuccess(List<City> cityList);

    void getDataProvinceSuccess(List<Province> provinceList);

    void getDataWardsSuccess(List<Wards> wardsList);

    void getDataDressPersonalError(int keyTypeErrorEmail);

    void getDataDressPersonalSuccess();
}
