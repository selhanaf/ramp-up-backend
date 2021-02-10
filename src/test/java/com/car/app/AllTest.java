package com.car.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.car.app.controller.CarDaoTest;
import com.car.app.controller.CarServiceTest;

@RunWith(Suite.class)
@SuiteClasses({CarServiceTest.class, CarDaoTest.class })
public class AllTest {

}
