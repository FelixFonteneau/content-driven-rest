package com.felix.fonteneau.contentdrivenrest.model.filter;

import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandomFilter implements Filter {
    private Double probabilityTrue = 0.5;
    private Random random = new Random();

    @Override
    public boolean filter(Content content, ApplicationData appData) {
        return random.nextDouble() < this.probabilityTrue;
    }
}
