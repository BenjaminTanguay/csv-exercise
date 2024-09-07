package com.example.dao;

import java.util.List;
import java.util.Map;

public interface Dao {
    List<Map<String, String>> filterRecords(Map<String, String> filters);
}
