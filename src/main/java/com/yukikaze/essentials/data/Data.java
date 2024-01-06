package com.yukikaze.essentials.data;

import com.yukikaze.essentials.Essentials;

import java.util.Objects;

public class Data {

    public DataStorage data;

    public static DataStorage getDataStorage() {

        return switch (Objects.requireNonNull(Essentials.config.getString("general.data_storage"))) {
            case "mysql" -> new Mysql();
            case "sqlite" -> new Sqlite();
            default -> null;
        };

    }

}
