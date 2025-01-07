package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private final List<T> elements;

    private Streams(List<T> elements) {
        this.elements = new ArrayList<>(elements);
    }

    /**
     * Статический метод, который принимает коллекцию и создает новый объект Streams.
     */
    public static <E> Streams<E> of(List<E> list) {
        return new Streams<>(list);
    }

    /**
     * Оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбда-выражении.
     */
    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> filteredElements = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filteredElements.add(element);
            }
        }
        elements.clear();
        elements.addAll(filteredElements);
        return this;
    }

    /**
     * Преобразует каждый элемент в другой тип.
     */
    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper) {
        List<R> transformedElements = new ArrayList<>();
        for (T element : elements) {
            transformedElements.add(mapper.apply(element));
        }
        return new Streams<>(transformedElements);
    }

    /**
     * Преобразует элементы в мапу, где ключ и значение определяются переданными функциями.
     */
    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Map<K, V> result = new HashMap<>();
        for (T element : elements) {
            K key = keyMapper.apply(element);
            V value = valueMapper.apply(element);
            result.put(key, value);
        }
        return result;
    }
}