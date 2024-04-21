package com.codecool;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Task {
    private long id;
    private final String description;
    private final boolean finished;
}
