package com.java.java_15.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// ЗАДАНИЕ 15
/*Изменить программу с предыдущего задания так, чтобы объекты
хранились в базе данных PostgreSQL вместо памяти компьютера.*/


// ЗАДАНИЕ 16
/*Создать связь Один-ко-многим между сущностями из предыдущего
задания и проверить работу lazy loading.*/

/*
Lazy loading (ленивая загрузка) - это стратегия загрузки данных,
при которой ресурсы загружаются только в момент их фактического использования, а не заранее. То есть, данные загружаются по требованию.
*/

// ЗАДАНИЕ 17
/*Добавить возможность фильтрации по всем полям всех классов с
использованием Criteria API в Hibernate для программы из предыдущего
задания. Добавить эндпоинты для каждой фильтрации.*/

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
