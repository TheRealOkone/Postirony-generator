#include "stylehelper.h"


QString StyleHelper::getCloseStyleSheet(bool dark)
{
    if (!dark)
    {
    return "QToolButton { "
           "image: url(:/buttons/Close.png);"
           "background-color: #292929; "
           "icon-size: 12px;"
           "padding-left: 10px;"
           "padding-right: 10px;"
           "padding-top: 5px;"
           "padding-bottom: 5px;"
           "border: 1px solid #292929; "
           "}"
           "QToolButton:hover {"
           "image: url(:/buttons/CloseHover.png); "
           "}"
           "QToolButton:pressed { "
           "image: url(:/buttons/CloseHover.png);"
           "background-color: #de8e37; "
           "border: 1px solid #ff0000; "
           "}";
    }
    else
    {
        return "QToolButton { "
               "image: url(:/buttons/Close.png);"
               "background-color: #de8e37; "
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #de8e37; "
               "}"
               "QToolButton:hover {"
               "image: url(:/buttons/CloseHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/CloseHover.png);"
               "background-color: #292929; "
               "border: 1px solid #ff0000; "
               "}";
    }
}

QString StyleHelper::getMaximizeStyleSheet(bool dark)
{
    if (!dark)
    {
        return "QToolButton { "
               "image: url(:/buttons/Maximize.png);"
               "background-color: #292929;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #292929; "
               "}"
               "QToolButton:hover {"
               "image: url(:/buttons/MaximizeHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/MaximizeHover.png);"
               "background-color: #de8e37; "
                "border: 1px solid #0015ff; "
               "}";
    }
    else
    {
        return "QToolButton { "
               "image: url(:/buttons/Maximize.png);"
               "background-color: #de8e37;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #de8e37; "
               "}"
               "QToolButton:hover {"
               "image: url(:/buttons/MaximizeHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/MaximizeHover.png);"
               "background-color: #292929; "
               "border: 1px solid #0015ff; "
               "}";
    }
}

QString StyleHelper::getRestoreStyleSheet(bool dark)
{
    if (!dark)
    {
        return "QToolButton { "
               "image: url(:/buttons/Restore.png);"
               "background-color: #292929;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #292929; "
               "}"
               "QToolButton:hover {"
               "image: url(:/buttons/RestoreHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/RestoreHover.png);"
               "background-color: #de8e37; "
               "border: 1px solid #0015ff; "
               "}";
    }
    else
    {
        return "QToolButton { "
               "image: url(:/buttons/Restore.png);"
               "background-color: #de8e37;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #de8e37; "
               "}"
               "QToolButton:hover {"
               "image: url(:/buttons/RestoreHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/RestoreHover.png);"
               "background-color: #292929; "
               "border: 1px solid #0015ff; "
               "}";
    }
}

QString StyleHelper::getMinimizeStyleSheet(bool dark)
{
    if (!dark)
    {
        return "QToolButton { "
               "image: url(:/buttons/Minimize.png);"
               "background-color: #292929;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #292929; "
               "}"
               "QToolButton:hover { "
               "image: url(:/buttons/MinimizeHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/MinimizeHover.png);"
               "background-color: #de8e37; "
               "border: 1px solid #0015ff; "
               "}";
    }
    else
    {
        return "QToolButton { "
               "image: url(:/buttons/Minimize.png);"
               "background-color: #de8e37;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #de8e37; "
               "}"
               "QToolButton:hover { "
               "image: url(:/buttons/MinimizeHover.png); "
               "}"
               "QToolButton:pressed { "
               "image: url(:/buttons/MinimizeHover.png);"
               "background-color: #292929; "
               "border: 1px solid #0015ff; "
               "}";
    }
}


QString StyleHelper::getMenuStyleSheet(bool dark)
{
    if (!dark)
    {


        return "QToolButton { "
               "color: #a3a3a3;"
               "background-color: #292929;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #292929; "
               "}"
               "QToolButton:hover {"
               "color: white;"
               "}"
               "QToolButton:pressed { "
               "color: white; "
               "background-color: #de8e37; "
               "}";


    }
    else
    {
        return "QToolButton { "
               "color: blue;"
               "background-color: #de8e37;"
               "icon-size: 12px;"
               "padding-left: 10px;"
               "padding-right: 10px;"
               "padding-top: 5px;"
               "padding-bottom: 5px;"
               "border: 1px solid #de8e37; "
               "}"
               "QToolButton:hover {"
               "color: white;"
               "}"
               "QToolButton:pressed { "
               "color: white; "
               "background-color: #292929; "
               "}";
    }
}

