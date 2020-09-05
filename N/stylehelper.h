#ifndef STYLEHELPER_H
#define STYLEHELPER_H

#include <QString>

class StyleHelper
{
public:

    static QString getCloseStyleSheet(bool dark);
    static QString getMaximizeStyleSheet(bool dark);
    static QString getRestoreStyleSheet(bool dark);
    static QString getMinimizeStyleSheet(bool dark);
    static QString getMenuStyleSheet(bool dark);


};

#endif // STYLEHELPER_H
