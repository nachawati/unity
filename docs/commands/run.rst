Run
===

Execute local script or query

Usage
-----

**dgms** **run** [OPTIONS] <expression>...

.. list-table::
   :widths: 20 80
   :stub-columns: 1

   * - Parameters:
     - | <expression>...   
       | 	Script or query to execute

   * - Options:
     - | **-h**, **--help**
       |    Show this help message and exit.
       | **-e**, **--engine** = <engine>
       |    Specify analytics engine
       |      Default: jsoniq10
       | **--mode** = <mode>
       |    Specify symbolics mode
       |      Default: pyomo
       | **-r**, **--results** = <results>
       |    Write results to file
       | **-t**, **--timing**
       |    Print timing information
       | **-m**, **--memory**
       |    Print memory usage information
       | **-q**, **--query**
       |    Execute query expression
       | **--disable-source-transformation**
       |    Disable source code transformation

