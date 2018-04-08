package com.community.dao;

import com.community.entity.Image;

public interface ImageDao {
    Image findById(Integer id);

    boolean insert(Image image);
}
