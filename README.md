# Seattle-Library-Management-System

**Author:** Wang, Yinuo

**Last Update:** 2021.12.12

## Introduction

This is a library management system application developed with the [Seattle Public Library’s Collection Inventory Dataset](https://data.seattle.gov/Community/Library-Collection-Inventory/6vkj-f5xf) to realize basic library functionality for both reader and library administrators, including login/create/manage account, borrow/return book, recommend new book, etc.

In addition, this application can work with ROS to control a moveable robot providing book and activity navigation service. 
To implement this navigation functionality, you must implement [Seattle_turtlebot_nav_ros](https://github.com/AWang-Cabin/seattle_turtlebot_nav_ros) on your linux-PC as well. However, this step is not required, this Java application can run independently as long as you don’t use the navigation in it.

## Build & Run



## Dependency
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
    * [LCM main page](https://lcm-proj.github.io/)
* MySQL (test with mysql-5.7)
* [Seattle_turtlebot_nav_ros](https://github.com/AWang-Cabin/seattle_turtlebot_nav_ros)
