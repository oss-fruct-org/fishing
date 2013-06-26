import QtQuick 1.1
import com.nokia.symbian 1.1

PageStackWindow {
    id: appWindow

    initialPage: mainPage

    MainPage {
        id: mainPage
    }

    FontLoader {
        id: fishing_font
        source: "CorridaC-Regular.ttf"
    }

    Page {
        id: aboutPage
        tools: aboutTools
        orientationLock: PageOrientation.LockPortrait

        ToolBarLayout {
            id: aboutTools
            ToolButton {
                iconSource: "toolbar-back"
                anchors.left: (parent === undefined) ? undefined : parent.left
                onClicked: pageStack.pop();
            }
        }

        function load() {
            aboutLoader.source = "AboutPage.qml"
        }

        Loader {id: aboutLoader; anchors.fill: parent;}
    }

    Menu {
        id: myMenu
        visualParent: pageStack

        MenuLayout {
            MenuItem {
                text: "О программе"
                onClicked: {
                    myMenu.close();
                    aboutPage.load();
                    pageStack.push(aboutPage);
                }
            }
        }
    }
}
