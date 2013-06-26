#include <QtGui/QApplication>
#include <QGLWidget>
#include <QtDeclarative>
#include <qplatformdefs.h>
#include <imagewithborder.h>

// Lock orientation in Symbian
#ifdef Q_OS_SYMBIAN
#include <eikenv.h>
#include <eikappui.h>
#include <aknenv.h>
#include <aknappui.h>
#endif

Q_DECL_EXPORT int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    QDeclarativeView view;

//    NetworkAccessManagerFactory* namFactory = new NetworkAccessManagerFactory();
//    namFactory->setProxy(QNetworkProxy::HttpProxy, "127.0.0.1", 8888);
//    view.engine()->setNetworkAccessManagerFactory(namFactory);

    view.setViewport(new QGLWidget());
    QObject::connect((QObject*)view.engine(), SIGNAL(quit()), &app, SLOT(quit()));

    qmlRegisterType<ImageWithBorder>("fishing", 1, 0, "ImageWithBorder");

    // prefix of path to images
    QString appPath = QApplication::applicationDirPath();
    view.rootContext()->setContextProperty("applicationPath", "file:///" + appPath + "/");

#ifdef Q_OS_SYMBIAN
    // Lock orientation to portrait in Symbian
    CAknAppUi* appUi = dynamic_cast<CAknAppUi*> (CEikonEnv::Static()->AppUi());
    TRAP_IGNORE(
        if (appUi)
            appUi->SetOrientationL(CAknAppUi::EAppUiOrientationPortrait);
    )
#endif

#ifdef Q_OS_SYMBIAN
    view.setSource(QUrl("/qml/main.qml"));
#else
    view.setSource(QUrl("../../../qml/fishing/main.qml"));
#endif
    view.showFullScreen();

    return app.exec();
}
