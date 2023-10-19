# Лабораторная работа №2 по предмету «Параллельное программирование»

## Постановка задачи
Написать программу для решения систем линейных алгебраических уравнений общего вида. Алгоритмом решения задачи был выбран метод Гаусса.

## Исследование
Ниже представлены графики взаимосвязи между количеством потоков и временем нахождения решения СЛАУ. Размерность матрицы для каждого исследования была равной 1000 на 1000.
![1](https://github.com/fug0/hsai_java_lab2/assets/60321444/617368de-5e62-434d-a0af-c46bf739ac04)
![2](https://github.com/fug0/hsai_java_lab2/assets/60321444/552b3709-a31d-4d52-ad16-479c0aa6445d)
![3](https://github.com/fug0/hsai_java_lab2/assets/60321444/47de631e-147c-4a34-a590-b0a86e000cf9)
На графиках ниже видно, что слишком большое количество тредов только ухудшает показатели времени и начинает прослеживаться линейная зависимость. Это связано с затратами времени на создание тредов.
