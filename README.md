# HelloUser
**Описание работы приложения**
* На первом экране пользователю предлагается ввести свой логин, пароль и номер телефона.
* Для каждого поля определены ограничения на вводимые данные
* Когда все поля заполнены верно, кнопка "Login" становится доступной и открывает второй экран приложения
* Также при нажатии на кнопку "Login" сохраняются логин и пароль пользователя, флаг, отмечающий, что логин и пароль уже введены
* Пароль хранится в зашифровонном виде
* На втором экране поле для ввода пароля, если он совпадает с введенным на 1 экране, то показывается приветствие и изображение
* При запуске приложения проверяется, вводили ли уже логин с паролем, если да, то открывается сразу второй экран

**Использованные библиотеки**
1. Input Mask RedMadRobot - маска для ввода номера телефона
2. Picasso - удобная работа с изображениями
3. ButterKnife - байндинг элементов View во время кодогенерации, улучшает работу приложения на устройстве пользователя
4. Crouton - всплывающие уведомления
5. Jasypt - шифрование

Обзор приложения - [YouTube](https://youtu.be/_BkCodQsfvo)

--------------------------------------


**Application review**
* There are three input fields on the first screen: phone number, login and password
* There is input validation for each field
* The "Login" button becomes enabled when all three fields are filled out correct. Then on click the second screen comes to the top.
* All the user input data going to be saved at the moment button "login" pressed. The password is stored into device encrypted. Also it saves the state of being already logined. Shared preferences was used to save data.
* User meets password input field at the second screen when opening the app after creating login and password. They need to input correct password to be able to go to the main screen. 
* At the main screen user sees the greeting and pic, downloaded from web

**Libraries used:**
1. Input Mask RedMadRobot - user input masking library
2. Picasso - A powerful image downloading and caching library for Android
3. ButterKnife - Using Java annotations, makes Android development better by simplifying common tasks
4. Crouton - context sensitive notifications for Android
5. Jasypt - a java library which allows the developer to add basic encryption capabilities

Application review - [YouTube](https://youtu.be/_BkCodQsfvo)
