package com.example.demo.Facade;

import com.example.demo.Model.SanphamEntity;

import java.util.List;

public interface SearchProduct {
    List<SanphamEntity> Criteria(List<SanphamEntity> products);
}
