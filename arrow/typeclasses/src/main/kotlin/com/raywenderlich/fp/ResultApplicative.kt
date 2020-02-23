package com.raywenderlich.fp

// Applicative for the Result data type
fun <T> justResult(value: T) = Success(value)

fun <E, T, R> Result<E, T>.ap(fn: Result<E, (T) -> R>): Result<E, R> = when (fn) {
  is Success<(T) -> R> -> map(fn.a)
  is Error<E> -> when (this) {
    is Success<T> -> Error(fn.e)
    is Error<E> -> Error(this.e)
  }
}