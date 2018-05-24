package com.chunmi.annualconvention.dao;

import com.chunmi.annualconvention.po.PictureConfig;
import java.util.List;

public interface PictureConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PictureConfig record);

    PictureConfig selectByPrimaryKey(Long id);

    List<PictureConfig> selectAll();

    int updateByPrimaryKey(PictureConfig record);
    
    PictureConfig selectByType(String type);
    
    int  delByType(String type);
    
}