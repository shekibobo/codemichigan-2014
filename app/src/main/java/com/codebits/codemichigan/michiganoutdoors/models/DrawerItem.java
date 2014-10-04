package com.codebits.codemichigan.michiganoutdoors.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by kenny on 10/3/14.
 */
@NoArgsConstructor
public class DrawerItem {

    @Getter @Setter
    String name;

    @Getter @Setter
    Boolean check;


    public DrawerItem(String name) {
        this.name = name;
        this.check = false;
    }
}
