package com.example.jezuz1n.hairly.mappers;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.models.dto.ShopMapVO;

/**
 * Created by jezuz1n on 31/05/2017.
 */

public class Mapper {

    public static ShopMapVO mapToVOfromDTO(ShopDTO shop){
        ShopMapVO aux = new ShopMapVO();

        if(shop.getNick()!=null){
            aux.setNick(shop.getNick());
        }

        if(shop.getLatitude()!=null){
            aux.setLatitude(shop.getLatitude());
        }

        if(shop.getLongitude()!=null){
            aux.setLongitude(shop.getLongitude());
        }

        if(shop.getUid()!=null){
            aux.setUid(shop.getUid());
        }

        return aux;
    }

}
