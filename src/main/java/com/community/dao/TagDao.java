package com.community.dao;

import com.community.entity.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> findAll();

    Tag findById(Integer id);

    List<Tag> findAllByArticleId(Integer id);
}
