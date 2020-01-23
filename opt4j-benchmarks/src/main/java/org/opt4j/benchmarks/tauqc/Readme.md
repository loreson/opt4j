# TAUQC Triangle area under the quadratic curve maximizer

This problem is about maximizing the area.

A triangle is constructed, by placing one point on the graph and the other two on the x-axis.

Two parameters a and b (f(x) = a*x² + b * x).

Two parameters xMin and xMax for the boundaries.

Example parameterization:

a = -1

b = 4

xMin = 0

xMax = 5

f(x) = a*x² + b * x

A_t(x) = 1/2 * g * h = 1/2 * x * (a*x² + b * x)

**Running the optimizer results in A_t_max(2.66) = 4.74**

So for this parameterization, the maximum area of the triangle under the curve is 4.74 (at x=2.66).


Idea from [https://www.abiturma.de/mathe-lernen/analysis/besondere-aufgabentypen/extremwertaufgaben]
