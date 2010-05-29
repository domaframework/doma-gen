/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.extension.gen.it;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.seasar.doma.extension.gen.internal.util.JdbcUtil;
import org.seasar.doma.extension.gen.task.Gen;

/**
 * @author taedium
 * 
 */
public class ItGen extends Gen {

    @Override
    protected void doPrepare() {
        super.doPrepare();
        Connection connection = JdbcUtil.getConnection(dataSource);
        try {
            Statement statement = JdbcUtil.createStatement(connection);
            try {
                statement
                        .executeUpdate("create table department(department_id integer not null primary key, department_no integer not null,department_name varchar(20),location varchar(20) default 'tokyo', version integer)");
                statement
                        .executeUpdate("create table address(address_id integer not null primary key, street varchar(20), version integer)");
                statement
                        .executeUpdate("create table employee(employee_id integer not null primary key, employee_no integer not null ,employee_name varchar(20),manager_id integer,hiredate date,salary numeric(7,2),department_id integer,address_id integer,version integer, constraint fk_department_id foreign key(department_id) references department(department_id), constraint fk_address_id foreign key(address_id) references address(address_id))");
                statement
                        .executeUpdate("create table comp_key_department(department_id1 integer not null, department_id2 integer not null, department_no integer not null,department_name varchar(20),location varchar(20) default 'tokyo', version integer, constraint pk_comp_key_department primary key(department_id1, department_id2))");
                statement
                        .executeUpdate("create table comp_key_address(address_id1 integer not null, address_id2 integer not null, street varchar(20), version integer, constraint pk_comp_key_address primary key(address_id1, address_id2))");
                statement
                        .executeUpdate("create table comp_key_employee(employee_id1 integer not null, employee_id2 integer not null, employee_no integer not null ,employee_name varchar(20),manager_id1 integer,manager_id2 integer,hiredate date,salary numeric(7,2),department_id1 integer,department_id2 integer,address_id1 integer,address_id2 integer,version integer, constraint pk_comp_key_employee primary key(employee_id1, employee_id2), constraint fk_comp_key_department_id foreign key(department_id1, department_id2) references comp_key_department(department_id1, department_id2), constraint fk_comp_key_address_id foreign key(address_id1, address_id2) references comp_key_address(address_id1, address_id2))");
                statement
                        .executeUpdate("alter table department add constraint department_uk1 unique(department_no)");
                statement
                        .executeUpdate("alter table comp_key_department add constraint comp_key_department_uk1 unique(department_no)");
                statement
                        .executeUpdate("alter table employee add constraint employee_uk1 unique(address_id)");
                statement
                        .executeUpdate("alter table comp_key_employee add constraint comp_key_employee_uk1 unique(address_id1, address_id2)");
                statement
                        .executeUpdate("create table category(id integer not null primary key, name varchar(20), version integer)");
                statement
                        .executeUpdate("create table product(id integer not null primary key, primary_category_id integer not null, secondary_category_id integer not null ,version integer, constraint product_fk1 foreign key(primary_category_id) references category(id), constraint product_fk2 foreign key(secondary_category_id) references category(id))");
                statement
                        .executeUpdate("create table product2(id integer not null primary key, category integer not null, version integer, constraint product2_fk1 foreign key(category) references category(id))");
                statement
                        .executeUpdate("create table integer_table (integer_column integer, version integer)");
                statement
                        .executeUpdate("create table int_table (int_column int, version integer)");
                statement
                        .executeUpdate("create table double_table (double_column double, version integer)");
                statement
                        .executeUpdate("create table double_precision_table (double_precision_column double precision, version integer)");
                statement
                        .executeUpdate("create table float_table (float_column float, version integer)");
                statement
                        .executeUpdate("create table varchar_table (varchar_column varchar, version integer)");
                statement
                        .executeUpdate("create table varchar_ignorecase_table (varchar_ignorecase_column varchar_ignorecase, version integer)");
                statement
                        .executeUpdate("create table char_table (char_column char, version integer)");
                statement
                        .executeUpdate("create table character_table (character_column character, version integer)");
                statement
                        .executeUpdate("create table longvarchar_table (longvarchar_column longvarchar, version integer)");
                statement
                        .executeUpdate("create table date_table (date_column date, version integer)");
                statement
                        .executeUpdate("create table time_table (time_column time, version integer)");
                statement
                        .executeUpdate("create table timestamp_table (timestamp_column timestamp, version integer)");
                statement
                        .executeUpdate("create table datetime_table (datetime_column datetime, version integer)");
                statement
                        .executeUpdate("create table decimal_table (decimal_column decimal, version integer)");
                statement
                        .executeUpdate("create table numeric_table (numeric_column numeric, version integer)");
                statement
                        .executeUpdate("create table boolean_table (boolean_column boolean, version integer)");
                statement
                        .executeUpdate("create table bit_table (bit_column bit, version integer)");
                statement
                        .executeUpdate("create table tinyint_table (tinyint_column tinyint, version integer)");
                statement
                        .executeUpdate("create table smallint_table (smallint_column smallint, version integer)");
                statement
                        .executeUpdate("create table bigint_table (bigint_column bigint, version integer)");
                statement
                        .executeUpdate("create table real_table (real_column real, version integer)");
                statement
                        .executeUpdate("create table varbinary_table (varbinary_column varbinary, version integer)");
                statement
                        .executeUpdate("create table longvarbinary_table (longvarbinary_column longvarbinary, version integer)");
                statement
                        .executeUpdate("create view employeeview as select * from employee");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                JdbcUtil.close(statement);
            }
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
