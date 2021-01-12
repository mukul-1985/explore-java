package com.feature.sealed.interfaces;

public sealed interface Expr
    permits ConstantExpr, TimesExpr, PlusExpr, NegExpr {
}
