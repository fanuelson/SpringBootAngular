package com.foundation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.foundation.enums.MedidaEnum;

@RestController
@RequestMapping("medidas")
@RequestScope
public class MedidaController {

	@RequestMapping
	public MedidaEnum[] findAll() {
		return MedidaEnum.values();
	}
}
