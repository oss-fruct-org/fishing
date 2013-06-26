TARGET = fishing
TEMPLATE = app

QT += declarative opengl network

symbian:TARGET.UID3 = 0xE2A338E9

# Allow network access on Symbian
symbian:TARGET.CAPABILITY += NetworkServices

# If your application uses the Qt Mobility libraries, uncomment the following
# lines and add the respective components to the MOBILITY variable.
 CONFIG += mobility
 MOBILITY += location

# Add dependency to Symbian components
 CONFIG += qt-components qmsystem2

# meego harmattan sdk defines
exists($$QMAKE_INCDIR_QT"/../qmsystem2/qmkeys.h"):!contains(MEEGO_EDITION,harmattan): {
    MEEGO_VERSION_MAJOR     = 1
    MEEGO_VERSION_MINOR     = 0
    MEEGO_VERSION_PATCH     = 0
    MEEGO_EDITION           = harmattan
    DEFINES += MEEGO_EDITION_HARMATTAN
}

RESOURCES += \
    res.qrc

HEADERS += \
    imagewithborder.h

SOURCES += main.cpp \
    imagewithborder.cpp

OTHER_FILES = \
    qml/fishing/*.js \
    qml/fishing/*.qml \
    qml/fishing/*.xml \
    qml/fishing/*.html \
    qml/fishing/*.ttf \
    qml/fishing/AboutPage.qml

unix {
    #VARIABLES
    isEmpty(PREFIX) {
        PREFIX = $$[QT_INSTALL_PREFIX]
    }
}
message(prefix is $$PREFIX)

# prefix for search shared files
DATAPREFIX=$$PREFIX/share
DEFINES += DATAPREFIX=\\\"$$DATAPREFIX\\\"
message(dataprefix is $$DATAPREFIX)

BINDIR = $$PREFIX/bin
DATADIR =$$PREFIX/share

target.path = $$BINDIR
INSTALLS += target

# enable booster
CONFIG += qdeclarative-boostable
QMAKE_CXXFLAGS += -fPIC -fvisibility=hidden -fvisibility-inlines-hidden
QMAKE_LFLAGS += -pie -rdynamic

qml.path = $$DATADIR/qml
qml.files += $${OTHER_FILES}
INSTALLS += qml

fishes.path = $$DATADIR/qml/fishes
fishes.files += qml/fishing/fishes/*.jpg
INSTALLS += fishes

# desktop file
desktop.path = /usr/share/applications
desktop.files = $${TARGET}.desktop
INSTALLS += desktop

contains(MEEGO_EDITION,harmattan):{
    pixmapmeego.path = /usr/share/icons/hicolor/scalable/apps
    pixmapmeego.files += $${TARGET}.svg
    INSTALLS += pixmapmeego
}
