.. role:: xquery(code)
   :language: xquery

analytics Module
==========

**http://dgms.io/modules/analytics**

This module provides core analytical operators for decision guidance (DG)
analytics.


----------

Function Summary
**********

----------

.. raw:: html

  <code class="function-summary"><a href="#calibrate-1">calibrate</a>($kwargs)</code>

Calibrate analytical model parameters


----------

.. raw:: html

  <code class="function-summary"><a href="#compute-1">compute</a>($kwargs)</code>

Computes an analytical model with parametric input.


----------

.. raw:: html

  <code class="function-summary"><a href="#instantiate-2">instantiate</a>($items, $solution)</code>

Instantiate parametric object


----------

.. raw:: html

  <code class="function-summary"><a href="#maximize-1">maximize</a>($kwargs)</code>

Find decision variable values that maximize analytical model objective


----------

.. raw:: html

  <code class="function-summary"><a href="#minimize-1">minimize</a>($kwargs)</code>

Find decision variable values that minimize analytical model objective


----------

.. raw:: html

  <code class="function-summary"><a href="#parameter-0">parameter</a>() external</code>

Constructs a parameter object.


----------

.. raw:: html

  <code class="function-summary"><a href="#placeholder-0">placeholder</a>() external</code>

Constructs a placeholder object.


----------

.. raw:: html

  <code class="function-summary"><a href="#resolve-model-1">resolve-model</a>($model) as function (*)</code>

Resolve analytical model.


----------

.. raw:: html

  <code class="function-summary"><a href="#symbolify-1">symbolify</a>($items)</code>

Constructs a symbolic object


----------

.. raw:: html

  <code class="function-summary"><a href="#variable-0">variable</a>() external</code>

Constructs a variable object.


----------

Functions
**********

----------

.. _calibrate-1:

analytics:calibrate#1
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic function analytics:calibrate($kwargs)</code>


Calibrate analytical model parameters


Parameters
^^^^^^^^^^

- **$kwargs**


----------

.. _compute-1:

analytics:compute#1
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function analytics:compute($kwargs)</code>


Computes an analytical model with parametric input.


Parameters
^^^^^^^^^^

- **$kwargs**


----------

.. _instantiate-2:

analytics:instantiate#2
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic function analytics:instantiate($items, $solution)</code>


Instantiate parametric object


Parameters
^^^^^^^^^^

- **$items**

- **$solution**


----------

.. _maximize-1:

analytics:maximize#1
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic function analytics:maximize($kwargs)</code>


Find decision variable values that maximize analytical model objective


Parameters
^^^^^^^^^^

- **$kwargs**


----------

.. _minimize-1:

analytics:minimize#1
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic function analytics:minimize($kwargs)</code>


Find decision variable values that minimize analytical model objective


Parameters
^^^^^^^^^^

- **$kwargs**


----------

.. _parameter-0:

analytics:parameter#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function analytics:parameter() external</code>


Constructs a parameter object.



----------

.. _placeholder-0:

analytics:placeholder#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function analytics:placeholder() external</code>


Constructs a placeholder object.



----------

.. _resolve-model-1:

analytics:resolve-model#1
##########
.. raw:: html

  <code class="function-summary">declare %private function analytics:resolve-model($model) as function (*)</code>


Resolve analytical model.


Parameters
^^^^^^^^^^

- **$model**

Returns
^^^^^^^^^^

**function (*)**


----------

.. _symbolify-1:

analytics:symbolify#1
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic function analytics:symbolify($items)</code>


Constructs a symbolic object


Parameters
^^^^^^^^^^

- **$items**


----------

.. _variable-0:

analytics:variable#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function analytics:variable() external</code>


Constructs a variable object.


