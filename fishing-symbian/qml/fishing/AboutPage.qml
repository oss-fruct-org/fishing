// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import com.nokia.symbian 1.1
import "UIConstants.js" as UI
import "constants.js" as ExtrasConstants

Item {
    Column {
        anchors.fill: parent
        anchors.margins: 16
        spacing: 10

        Text {
            font.pixelSize: 26
            font.weight: Font.Bold
            wrapMode: Text.Wrap
            width: parent.width
            text: "Рыбалка в Карелии v1.0"
            horizontalAlignment: Text.AlignHCenter
            color: UI.COLOR_INVERTED_FOREGROUND
        }

        Text {
            font.pixelSize: 22
            width: parent.width
            wrapMode: Text.Wrap
            horizontalAlignment: Text.AlignHCenter
            text: "<font>&copy;</font>" + " 2012" + " Лаборатория FRUCT в ИТ-Парке " +
                  "Петрозаводского Государственного Университета (ПетрГУ)."
            color: ExtrasConstants.LIST_SUBTITLE_COLOR_INVERTED
        }

        Text {
            font.pixelSize: 22
            width: parent.width
            wrapMode: Text.Wrap
            horizontalAlignment: Text.AlignHCenter
            text: "Проект поддерживается грантом <b>ENPI Karelia KA-322</b>."
            color: ExtrasConstants.LIST_SUBTITLE_COLOR_INVERTED
        }

        Text {
            font.pixelSize: 22
            width: parent.width
            wrapMode: Text.Wrap
            horizontalAlignment: Text.AlignHCenter
            text: "При разработке приложения использованы материалы с сайтов <a style='color:#006abe' href='http://vedlozero.ru'>http://vedlozero.ru</a> и <a style='color:#006abe' href='http://ticrk.ru'>http://ticrk.ru</a>."
            color: ExtrasConstants.LIST_SUBTITLE_COLOR_INVERTED
            onLinkActivated: Qt.openUrlExternally(link)
        }

        Text {
            font.pixelSize: 22
            width: parent.width
            wrapMode: Text.Wrap
            horizontalAlignment: Text.AlignHCenter
            text: "Подробная информация на сайте <a style='color:#006abe' href='http://oss.fruct.org/projects/fishing'>http://oss.fruct.org/projects/fishing</a>."
            color: ExtrasConstants.LIST_SUBTITLE_COLOR_INVERTED
            onLinkActivated: Qt.openUrlExternally(link)
        }
    }
}
