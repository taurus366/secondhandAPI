package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.service.ClothCreateServiceModel;

import java.io.IOException;

public interface ClothService {

    ClothCreateServiceModel createNewCloth(ClothCreateServiceModel clothServiceModel) throws IOException;

}
