# Seattle-Library-Management-System

**Author:** Wang, Yinuo\
**Last Update:** 2021.12.12\
**Email:** ywang3781@gatech.edu

## Introduction

<img src="https://user-images.githubusercontent.com/69251304/145726243-9aefe150-7a63-40b4-9bd8-e2a470db3a87.png" width="450" height="320"/> <img src="https://user-images.githubusercontent.com/69251304/145726254-c10b7707-8586-4e98-b10f-0898de7a44fc.png" width="450" height="320"/><br/>

This is a library management system application developed with the [Seattle Public Library’s Collection Inventory Dataset](https://data.seattle.gov/Community/Library-Collection-Inventory/6vkj-f5xf) to realize basic library functionality for both reader and library administrators, including login/create/manage account, borrow/return book, recommend new book, etc.

In addition, this application can work with ROS to control a moveable robot providing book and activity navigation service. 
To implement this navigation functionality, you must implement [Seattle_turtlebot_nav_ros](https://github.com/AWang-Cabin/seattle_turtlebot_nav_ros) on your linux-PC as well. However, this step is not required, this Java application can run independently as long as you don’t use the navigation in it.

## Build & Run
* Import database into Mysql

   Project database file can be found in the `/Seattle-LibMS/db` folder
   
* Configure the database login info

   Go to the `/Seattle-LibMS/src/gt/util/DBUtil.java`, change the database Username and Password to yours. Current User=root, Password=666. 
* Check and use suitable JDBC drive

   I use JavaSE1.8 and MySQL-5.7 on my PC, if your java-version or mysql-version is different, please go to `/Seattle-LibMS/lib` folder to check if the mysql-java connector is suitable for your environment.
   
* Build and run the project

   If you use the Eclispe to open the project, you can run `/Seattle-LibMS/src/gt/view/LoginFrame.java` directly.\
   Or you can export the whole project to an executable jar file, project launch entry is also the `LoginFrame.java`.
   I also provide a runable jar under `/Seattle-LibMS/bin` folder.

* Login the account
   Here, I provide a reader account and a administor account for you to test the project:
   ```
   Account: Reader
   Username: ywang
   Password: 12345
   
   Account: Administor
   Username: admin001
   Password: 111111
   ```

## Dependency
* Ubuntu 18

   This project was developed and test only on Ubuntu 18.0 system, but it should be good on other system.
   Because the robot part can only be run on Linux, if you want to realize all project functionalities, please implement this application also on a linux system.
   
* MySQL (mysql-5.7)

* JDBC

   You can download your mysql-java jdbc from [here](https://mvnrepository.com/artifact/mysql/mysql-connector-java) and copy it into `YOUR_ECLIPSE_WS/Seattle-LibMS/lib`.
   
* LCM
    * Download package [lcm-1.4.0](https://github.com/AWang-Cabin/MiLAB-Cheetah-Software/releases/download/v1.0.0/lcm-1.4.0.zip) 
    * Unzip to /home and install (Must unzip to /home)
        ```
        cd lcm-1.4.0
        mkdir build && cd build
        cmake ..
        make -j4
        sudo make install
        sudo ldconfig
        ```
      * If meet make error, check your java jdk version
    * Copy the jar file to `Seattle-LibMS/lib`
      ```
      sudo cp YOUR_LCM_PACKAGE_PATH/lcm-1.4.0/build/lcm-java/lcm.jar YOUR_ECLIPSE_WS/Seattle-LibMS/lib
      ```
    * [LCM main page](https://lcm-proj.github.io/)
    
* [Seattle_turtlebot_nav_ros](https://github.com/AWang-Cabin/seattle_turtlebot_nav_ros)
