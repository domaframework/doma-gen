.. Doma-Gen documentation master file, created by
   sphinx-quickstart on Thu Feb 13 12:43:15 2014.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to Doma-Gen
===================

Doma-Gen は `Doma 2`_ のためのコードジェネレーターです。

Doma-Gen を使うと次のことができます。

* データベースのメタデータから Java や SQL のファイル一式を生成する
* SQLの実行結果から エンティティクラスのコードを生成する
* ディレクトリ中の SQL ファイルを検出し SQL のためのテストケースを生成する

Doma-Gen は Ant_ タスクとして作成されているため、 Ant はもちろん、 Gradle_ などのビルドツールから実行できます。

コードの生成には FreeMarker_ を使用しており、生成されるコードは FreeMarker の記法で自由にカスタマイズ可能です。

このドキュメントは複数のセクションから成ります。

* `User Documentation`_
* `About Doma-Gen`_
* `Links`_

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

.. _Doma 2: http://doma.readthedocs.org/ja/latest/
.. _Ant: http://ant.apache.org/
.. _Gradle: http://www.gradle.org/
.. _FreeMarker: http://freemarker.org/
