package com.credit.service.impl;

import com.credit.entity.Man;
import com.credit.repository.ManRepository;
import com.credit.service.ManService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Chan on 3/20/2017.
 */
@Service("manService")
public class ManServiceImpl implements ManService<Man>
{
    @Resource
    ManRepository manRepository;

    @Override
    public List<Man> save()
    {
        List<Man> men = new ArrayList<>();
        for (int i = 1; i < 20; i++)
        {
            Man man2 = new Man();
            man2.setUserId(i);
            man2.setUserNo(i);
            man2.setUserName("xiaoming");
//            if(i==10) { int a=1/0; }
            Man m =manRepository.save(man2);
            men.add(m);
        }
         return men;
    }
}
