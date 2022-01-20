#language:ru

Функционал: Cart API

  @test_data
  Сценарий: Добавление блюда в корзину
    * выбрано блюдо "Шефбургер Де Люкс"
    * блюдо "Шефбургер Де Люкс" добавлено в корзину
    * выбран способ получения заказа - доставка
    * выбрано блюдо "Шефбургер острый"
    * блюдо "Шефбургер острый" добавлено в корзину
    * выполнен переход в корзину
    * Присутвствует заголовок - У вас отличный вкус!
    * В корзине присутвствует блюдо "Шефбургер Де Люкс"
    * В корзине присутвствует блюдо "Шефбургер острый"
    * Итоговое значение суммы в корзине - корректно
    * Выполнено подтверждение заказа
    * форма заполняется значениями:
      | Улица и дом | Россия, Москва, Тверская улица, 1 |
      | Квартира    | 1                                 |
      | Подъезд     | 1                                 |
      | Этаж        | 1                                 |
      | Комментарий | test                              |
    * Кнопка 'Оплатить' не активна

