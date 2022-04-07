package com.xr.base.core;

import java.util.LinkedList;

/**
 * @author: forvoyager@outlook.com
 * @time: 2022-04-07 13:53:00
 * @description: 固定长度的List
 */
public class FixedLengthList<T> extends LinkedList<T> {
  private int capacity;

  public FixedLengthList(){
    this(16);
  }

  public FixedLengthList(int capacity){
    super();
    this.capacity = capacity;
  }

  @Override
  public boolean add(T t) {
    if(this.size()+1>this.capacity){
      super.removeFirst();
    }
    return super.add(t);
  }

  @Override
  public void addFirst(T t) {
    this.add(t);
  }

  @Override
  public void addLast(T t) {
    throw new IllegalStateException("not support.");
  }

  @Override
  public void add(int index, T element) {
    throw new IllegalStateException("not support.");
  }

  @Override
  public T remove(int index) {
    throw new IllegalStateException("not support.");
  }

  @Override
  public T remove() {
    throw new IllegalStateException("not support.");
  }

  @Override
  public T peek() {
    throw new IllegalStateException("not support.");
  }

  @Override
  public T poll() {
    throw new IllegalStateException("not support.");
  }

  @Override
  public void push(T t) {
    throw new IllegalStateException("not support.");
  }

  @Override
  public T pop() {
    throw new IllegalStateException("not support.");
  }
}
