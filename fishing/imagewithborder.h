#ifndef IMAGEWITHBORDER_H
#define IMAGEWITHBORDER_H

#include <QtCore>
#include <QDeclarativeItem>
#include <QPainter>
#include <QStyleOptionGraphicsItem>

class ImageWithBorder : public QDeclarativeItem
{
    Q_OBJECT
    Q_PROPERTY(QUrl source READ source WRITE setSource NOTIFY sourceChanged)
    Q_PROPERTY(int borderWidth READ borderWidth WRITE setBorderWidth NOTIFY borderWidthChanged)
public:
    explicit ImageWithBorder(QDeclarativeItem *parent = 0);

    QUrl source() const;
    void setSource(const QUrl &url);

    int borderWidth();
    void setBorderWidth(int width);

    void paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget = 0);
    
signals:
    void sourceChanged();
    void borderWidthChanged();
    
private:
    QUrl m_source;
    int m_borderWidth;
};

#endif // IMAGEWITHBORDER_H
