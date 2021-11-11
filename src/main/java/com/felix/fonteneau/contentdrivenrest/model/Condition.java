package com.felix.fonteneau.contentdrivenrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition {
    private List<String> filtersName;
    private List<Condition> OR;
    private List<Condition> AND;
}
