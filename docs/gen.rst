========
Gen task
========

.. contents::
   :depth: 3

Gen task is the only task in Doma-Gen.

The Gen task uses following data types:

* `EntityConfig`_
* `DaoConfig`_
* `SqlConfig`_
* `SqlTestCaseConfig`_

Top level parameters
====================

Top level parameters are as follows:

+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| Parameter                | Description                                                | Default value                    | Required |
+==========================+============================================================+==================================+==========+
| url                      | A database url of the form ``jdbc:subprotocol:subname``.   |                                  | YES      |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| user                     | A database user.                                           |                                  | YES      |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| password                 | A database password.                                       |                                  | YES      |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| driverClassName          | A JDBC driver class name.                                  | Inferred from the ``url`` value. |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| dialectName              | | A dialect name for a RDBMS.                              | Inferred from the ``url`` value. |          |
|                          | | One of following values is available:                    |                                  |          |
|                          | | ``h2``, ``hsqldb``, ``mysql``, ``postgres``,             |                                  |          |
|                          | | ``mssql``, ``oracle``,  ``db2``.                         |                                  |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| dialectClassName         | A dialect class name used in Doma runtime.                 | Inferred from the ``url`` value. |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| genDialectClassName      | | A dialect class name used in Doma-Gen runtime.           | Inferred from the ``url`` value. |          |
|                          | | The class must be in CLASSPATH of Doma-Gen runtime.      |                                  |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| schemaName               | A target schema.                                           |                                  |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| tableNamePattern         | A regex pattern of target tables. (case insensitive)       | ``.*``                           |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| ignoredTableNamePattern  | A regex pattern of non-target tables. (case insensitive)   | ``.*\$.*``                       |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| tableTypes               | | The types of target tables.                              | TABLE                            |          |
|                          | | For example, specify "TABLE, VIEW"                       |                                  |          |
|                          | | to include views in addition to tables.                  |                                  |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| versionColumnNamePattern | | The regex pattern of version columns. (case insensitive) |                                  |          |
|                          | | The corresponding entity properties will                 |                                  |          |
|                          | | be annotated with ``@Version``.                          |                                  |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| templateEncoding         | The encoding for Freemarker template files                 | UTF-8                            |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| templatePrimaryDir       | | The primary directory for Freemarker template files.     |                                  |          |
|                          | | User defined template files must be located              |                                  |          |
|                          | | in this directory.                                       |                                  |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+
| globalFactoryClassName   | | The full qualified name of a factory class.              | org.seasar.doma.                 |          |
|                          | | It must implement                                        | extension.gen.                   |          |
|                          | | ``org.seasar.doma.extension.gen.GlobalFactory``.         | GlobalFactory                    |          |
+--------------------------+------------------------------------------------------------+----------------------------------+----------+

Configuration data types
========================

EntityConfig
------------

This data type represents the configuration for entity and entity listener classes.
The parameter definitions are as follows:

+------------------------------+-----------------------------------------------------------------+---------------+----------+
| Parameter                    | Description                                                     | Default value | Required |
+==============================+=================================================================+===============+==========+
| generate                     | If ``true``, the Java files are generated.                      | true          |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| destDir                      | The destination directory for generated Java files.             | src/main/java |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| encoding                     | The encoding for generated Java files.                          | UTF-8         |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| overwrite                    | | If ``true``, the Java files of entity classes                 | true          |          |
|                              | | are overwritten.                                              |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| overwriteListener            | If ``true``, the Java files of entity listener                  | false         |          |
| overwriteListener            | classes are overwritten.                                        |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| packageName                  | The package for entity classes.                                 |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| entityPrefix                 | The prefix of entity classes.                                   |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| entitySuffix                 | The prefix of entity classes.                                   |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| superclassName               | | The full qualified name of superclass of entity classes.      |               |          |
|                              | | The class must be in CLASSPATH of Doma-Gen runtime.           |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| listenerSuperclassName       | | The full qualified name of superclass of                      |               |          |
|                              | | entity listener class.                                        |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| namingType                   | | A naming conversion.                                          |               |          |
|                              | | One of the following value is available:                      |               |          |
|                              | | ``none``,  ``snake_upper_case``,                              |               |          |
|                              | | ``snake_lower_case``, ``upper_case``, ``lower_case``.         |               |          |
|                              | | This value is reflected in                                    |               |          |
|                              | | the ``@Entity``' ``naming`` element.                          |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| entityPropertyClassNamesFile | The file used to resolve entity property classes.               |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| generationType               | | The generation type for entity identities.                    |               |          |
|                              | | One of the following value is available:                      |               |          |
|                              | | ``identity``, ``sequence``, ``table``.                        |               |          |
|                              | | This value is valid only if the table has single primary key. |               |          |
|                              | | This value is reflected in                                    |               |          |
|                              | | the ``@GeneratedValue``'s ``strategy`` element.               |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| initialValue                 | | The initial value for entity identities.                      |               |          |
|                              | | This value is valid only if the ``generationType`` is         |               |          |
|                              | | either ``sequence`` or ``table``.                             |               |          |
|                              | | This value is reflected in the ``initialValue`` element of    |               |          |
|                              | | either ``@SequenceGenerator`` or ``@TableGenerator``.         |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| allocationSize               | | The allocation size for entity identities.                    |               |          |
|                              | | This value is valid only if the ``generationType`` is         |               |          |
|                              | | either ``sequence`` or ``table``.                             |               |          |
|                              | | This value is reflected in the ``allocationSize`` element of  |               |          |
|                              | | either ``@SequenceGenerator`` or ``@TableGenerator``          |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| useAccessor                  | | If ``true``, accessor methods are generated for               | true          |          |
|                              | | entity class field.                                           |               |          |
|                              | | If ``false``, each entity class field are generated           |               |          |
|                              | | with a public modifier.                                       |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| useListener                  | | If ``true``, the code of entity listeners are generated.      | true          |          |
|                              | | Each listener is reflected in                                 |               |          |
|                              | | the ``@Entity``' ``listener`` element.                        |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| showDbComment                | | If ``true``, comments in a database are reflected             | true          |          |
|                              | | in JavaDoc comments.                                          |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| showCatalogName              | | If ``true``, a catalog name is reflected                      | false         |          |
|                              | | in the ``@Table``'s ``catalog`` element.                      |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| showSchemaName               | | If ``true``, a schema name is reflected                       | false         |          |
|                              | | in the ``@Table``'s ``schema`` element.                       |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| showTableName                | | If ``true``, a table name is reflected                        | true          |          |
|                              | | in the ``@Table``'s ``name`` element.                         |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| showColumnName               | | If ``true``, a column name is reflected                       | true          |          |
|                              | | in the ``@Column``'s ``name`` element.                        |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| originalStatesPropertyName   | | The property to be annotated with ``@OriginalStates``.        |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| sql                          | | An SQL statement. Code of an entity class is generated from   |               |          |
|                              | | the result of the SQL statement execution.                    |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+
| entityName                   | | The entity class name which will be generated from            | Example       |          |
|                              | | the ``sql``.                                                  |               |          |
|                              | | This value is valid only if the ``sql`` have a value.         |               |          |
+------------------------------+-----------------------------------------------------------------+---------------+----------+

DaoConfig
---------

This data type represents the configuration for DAO interfaces.
The parameter definitions are as follows:

+-----------------+-----------------------------------------------------------------+---------------+----------+
| Parameter       | Description                                                     | Default value | Required |
+=================+=================================================================+===============+==========+
| generate        | If ``true``, the Java files are generated.                      | true          |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+
| destDir         | The destination directory for generated Java files.             | src/main/java |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+
| encoding        | The encoding for generated Java files.                          | UTF-8         |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+
| overwrite       | | If ``true``, the Java files of DAO interfaces                 | false         |          |
|                 | | are overwritten.                                              |               |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+
| packageName     | The package for DAO interfaces.                                 | example.dao   |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+
| suffix          | The suffix of DAO interfaces.                                   | Dao           |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+
| configClassName | | The full qualified name of a configuration class.             | false         |          |
|                 | | This value is reflected in the ``@Dao``'s ``config`` element. |               |          |
+-----------------+-----------------------------------------------------------------+---------------+----------+

SqlConfig
---------

This data type represents the configuration for SQL files.

This data type can generate two SQL files.
Each of the SQL files contains SELECT statement whose search condition is:

* An identifier only.
* The pair of an identifier and a version.

But there are exceptions:

* If an entity doesn't have the identifier, neither SQL files are generated.
* If an entity doesn't have the version, the second SQL file is not generated.

You can generate arbitrary SQL files if you prepare `Freemarker`_ template files.

The parameter definitions are as follows:

+------------+----------------------------------------------------+--------------------+----------+
| Parameter  | Description                                        | Default value      | Required |
+============+====================================================+====================+==========+
| generate   | If ``true``, SQL files are generated.              | true               |          |
+------------+----------------------------------------------------+--------------------+----------+
| destDir    | The destination directory for generated SQL files. | src/main/resources |          |
+------------+----------------------------------------------------+--------------------+----------+
| overwrite  | If ``true``, SQL files are overwritten.            | true               |          |
+------------+----------------------------------------------------+--------------------+----------+

SqlTestCaseConfig
-----------------

This data type represents the configuration for Java test cases for SQL files.

The parameter definitions are as follows:

+------------+------------------------------------------------------+---------------+----------+
| Parameter  | Description                                          | Default value | Required |
+============+======================================================+===============+==========+
| generate   | If ``true``, Java files for SQL tests are generated. | true          |          |
+------------+------------------------------------------------------+---------------+----------+
| destDir    | The destination directory for generated Java files.  | src/test/java |          |
+------------+------------------------------------------------------+---------------+----------+
| encoding   | The encoding for generated Java files.               | UTF-8         |          |
+------------+------------------------------------------------------+---------------+----------+

FileSet as a nested element
~~~~~~~~~~~~~~~~~~~~~~~~~~~

To find the target SQL files, use the ``FileSet`` element.

The SQL files meet following conditions:

* The extension of them is ``sql``.
* They are located in directories below a “META-INF” directory.

Run with Gradle
===============

You can use the Gen task with Gradle_.
Write your build script as follows:

.. code-block:: groovy

  configurations {
      domaGenRuntime
  }

  repositories {
      mavenCentral()
      maven {url 'https://oss.sonatype.org/content/repositories/snapshots/'}
  }

  dependencies {
      domaGenRuntime 'org.seasar.doma:doma-gen:2.27.0'
      domaGenRuntime 'org.postgresql:postgresql:9.3-1100-jdbc41'
  }

  task gen {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              entityConfig()
              daoConfig()
              sqlConfig()
          }
      }
  }

  task genTestCases {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              sqlTestCaseConfig {
                  fileset(dir: 'src/main/resources') {
                      include(name: 'META-INF/**/*.sql')
                  }
              }
          }
      }
  }

There are important points:

* Indicate dependencies on Doma-Gen and a JDBC driver with custom configurations ``domaGenRuntime``.
* Specify ``domagentask.properties`` to the ``ant.taskdef``'s ``resource`` parameter.
* Specify the classpath of ``domaGenRuntime`` to the ``ant.taskdef``'s ``classpath`` parameter.

Examples
========

We show you typical use cases using Gradle_.

Generating the whole set of files from database metadata
--------------------------------------------------------

Define the following task:

.. code-block:: groovy

  task gen {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              entityConfig()
              daoConfig()
              sqlConfig()
          }
      }
  }

This task generates following files:

* Java files for entity classes
* Java files for entity listeners classes
* Java files for  DAO interface
* SQL files

Generating an entity class file from an SQL
-------------------------------------------

Define the following task:

.. code-block:: groovy

  task genEntity {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              entityConfig(packageName: 'aaa.bbb',
                  entityName: 'GroupByDeptId',
                  sql: 'select dept_id, max(age) as max_age from emp group by dept_id')
          }
      }
  }

This task generates the following Java file:

.. code-block:: java

  package aaa.bbb;

  import org.seasar.doma.Column;
  import org.seasar.doma.Entity;

  @Entity
  public class GroupByDeptId {

      /** */
      @Column(name = "DEPT_ID")
      Integer deptId;

      /** */
      @Column(name = "MAX_AGE")
      Integer age;

      ...
  }

It is convenient to use a ``-P`` option of Gradle to pass parameters from a command line:

.. code-block:: bash

  $ ./gradlew genEntity -PentityName="GroupByDeptId" -Psql="select dept_id, max(age) from emp group by dept_id"

.. code-block:: groovy

  task genEntity {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              entityConfig(packageName: 'aaa.bbb',
                  entityName: entityName,
                  sql: sql)
          }
      }
  }

Generating SQL file test cases
------------------------------

Define the following task:

.. code-block:: groovy

  task genTestCases {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              sqlTestCaseConfig {
                  fileset(dir: 'src/main/resources') {
                      include(name: 'META-INF/**/*.sql')
                  }
              }
          }
      }
  }

Mapping entity properties to arbitrary classes
----------------------------------------------

You can generate entity classes whose property type is user defined type.
To do so, you have to define mappings in a properties file.

For example, suppose you want to generate the entity class ``example.entity.Employee`` and
the class have properties ``firstName`` and ``lastName``.
To map the properties to the ``example.domain.Name`` class,
write the properties file as follows:

.. code-block:: properties

  example.entity.Employee@.*Name$=example.domain.Name

The key part is ``example.entity.Employee@.*Name$`` and the value part is ``example.domain.Name``.

In the key part, the left side of ``@`` is the entity class name, and the right side of ``@``
is property name represented as a regex pattern.

With the definition, the following code is generated:

.. code-block:: java

  import example.domain.Name;

  @Entity
  public class Employee {
      @Id
      Integer id;
      Name firstName;
      Name lastName;
      ...
  }

To apply the definition to all entity classes not only to ``example.entity.Employee``,
remove ``example.entity.Employee@`` from the key part:

.. code-block:: properties

  .*Name$=example.domain.Name

Specify the properties file to the ``EntityConfig``'s ``entityPropertyClassNamesFile`` parameter:

.. code-block:: groovy

  task gen {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '') {
              entityConfig(entityPropertyClassNamesFile: 'name.properties')
              daoConfig()
              sqlConfig()
          }
      }
  }

Using custom template files
---------------------------

Default template files are located in
`the source code repository of Doma-Gen
<https://github.com/domaframework/doma-gen/tree/master/src/main/resources/org/seasar/doma/extension/gen/template>`_.

The template files are as follows:

+--------------------------------+--------------------------------------------------+----------------------------------------+
| Template file                  | Data model class                                 | Generated file                         |
+================================+==================================================+========================================+
| entity.ftl                     | org.seasar.doma.extension.gen.EntityDesc         | Java files for entity classes          |
+--------------------------------+--------------------------------------------------+----------------------------------------+
| entityListener.ftl             | org.seasar.doma.extension.gen.EntityListenerDesc | Java files for entity listener classes |
+--------------------------------+--------------------------------------------------+----------------------------------------+
| dao.ftl                        | org.seasar.doma.extension.gen.DaoDesc            | Java files for DAO interfaces          |
+--------------------------------+--------------------------------------------------+----------------------------------------+
| sqlTestCase.ftl                | org.seasar.doma.extension.gen.SqlTestCaseDesc    | Java files for SQL tests               |
+--------------------------------+--------------------------------------------------+----------------------------------------+
| selectById.sql.ftl             | org.seasar.doma.extension.gen.SqlDesc            | SQL files                              |
+--------------------------------+--------------------------------------------------+----------------------------------------+
| selectByIdAndVersion.sql.ftl   | org.seasar.doma.extension.gen.SqlDesc            | SQL files                              |
+--------------------------------+--------------------------------------------------+----------------------------------------+

To create custom template files, copy them and modify their contents without changing file names.
Then put them in the directory which is specified to the ``templatePrimaryDir`` parameter.

For example, if you put them in the directory "mytemplate",
specify "mytemplate" to the ``templatePrimaryDir`` parameter:

.. code-block:: groovy

  task gen {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '',
              templatePrimaryDir: 'mytemplate') {

              entityConfig()
              daoConfig()
              sqlConfig()
          }
      }
  }

Adding a copyright header to generated Java files
-------------------------------------------------

Define a copyright header in the ``lib.ftl`` file as follows:

::

  <#assign copyright>
  /*
   * Copyright 2008-2009 ...
   * All rights reserved.
   */
  </#assign>

Then put the the ``lib.ftl`` in a directory and
specify the directory to the ``templatePrimaryDir`` parameter:

.. code-block:: groovy

  task gen {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '',
              templatePrimaryDir: 'mytemplate') {

              entityConfig()
              daoConfig()
              sqlConfig()
          }
      }
  }

Adding an author JavaDoc comment to generated Java files
--------------------------------------------------------

Define an author in the ``lib.ftl`` file as follows:

::

  <#assign author="Nakamura">

Then put the the ``lib.ftl`` in a directory and
specify the directory to the ``templatePrimaryDir`` parameter:

.. code-block:: groovy

  task gen {
      group = 'doma-gen'
      doLast {
          ant.taskdef(resource: 'domagentask.properties',
              classpath: configurations.domaGenRuntime.asPath)
          ant.gen(url: 'jdbc:postgresql://127.0.0.1/example', user: '', password: '',
              templatePrimaryDir: 'mytemplate') {

              entityConfig()
              daoConfig()
              sqlConfig()
          }
      }
  }


.. links
.. _Gradle: http://www.gradle.org/
.. _FreeMarker: http://freemarker.org/
