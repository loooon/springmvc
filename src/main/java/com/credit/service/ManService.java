package com.credit.service;

import com.credit.entity.Man;

import java.util.List;

/**
 * Created by Michael Chan on 3/20/2017.
 */
public interface ManService<T extends Man>
{
    List<Man> save();
}
