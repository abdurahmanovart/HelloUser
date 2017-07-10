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


--------------------------------------
Just the first Android application.

**Application review**
* On the first screen there are 3 input fields: phone number, login and password
* Restrictions for each input field
* When are fields are filled correct, button "Login" becomes enabled, which opens the second screen
* Also, when "Login" button is pressed, login, password and flag, indicating that the username and password have already been entered, saving in Shared Preferences
* Password saving encrypted
* On the second screen, there is one field for password input, when entered and stored password match, opens greeting and picture are showing
* When the application starts, if login and password has already been entered, second screen opens immediately

**Used libraries**
1. Input Mask RedMadRobot - input mask for phone field input
2. Picasso - easy work with pictures
3. ButterKnife - View elements binding when compiling, faster application work on user's device
4. Crouton - instead of Toast
