package com.credit.repository;

import com.credit.entity.Man;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Michael Chan on 3/20/2017.
 */
public interface ManRepository extends JpaRepository<Man,Integer>
{
}
