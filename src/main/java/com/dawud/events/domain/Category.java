package com.dawud.events.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category implements Serializable {

        private static final long serialVersionUID = -7788619177798333713L;
        /**
         * Category ID
         */
        private String id;

        /**
         * Category Name
         */
        private String name;

        @Override
        public String toString() {
                return "Category{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        '}';
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}
