.. Doma-Gen documentation master file, created by
   sphinx-quickstart on Thu Feb 13 12:43:15 2014.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to Doma-Gen 2
=====================

Doma-Gen 2 is a code generation tool for `Doma 2`_.

Doma-Gen will generate:

* Java source code and SQL files from database metadata
* Java source code from the results of SQL executions
* test cases for SQL statements from SQL files

Doma-Gen is an Ant_ task, which can be used by many build tools such as Ant_ and Gradle_.

Doma-Gen generates various files using FreeMarker_.
You can customize all generation code by changing FreeMarker_ template files.

This document consists of following sections:

* `User Documentation`_
* `About Doma-Gen`_
* `Links`_

.. admonition:: Help improve our documentation
  :class: important

  Have feedback or a question? Found a typo?

  Please add an issue or pull request to
  https://github.com/domaframework/doma-gen and we’ll get back to you.


User Documentation
==================

.. toctree::
   :maxdepth: 2

   gen

About Doma-Gen
==============

.. toctree::
   :maxdepth: 1

   release-notes

Links
=====

* `GitHub repository <https://github.com/domaframework/doma-gen>`_
* `JavaDoc <http://www.javadoc.io/doc/org.seasar.doma/doma-gen>`_

.. _Doma 2: http://doma.readthedocs.org/
.. _Ant: http://ant.apache.org/
.. _Gradle: http://www.gradle.org/
.. _FreeMarker: http://freemarker.org/
