#include "imagewithborder.h"
#include <QDebug>

ImageWithBorder::ImageWithBorder(QDeclarativeItem *parent) :
    QDeclarativeItem(parent)
{
    setFlag(QGraphicsItem::ItemHasNoContents, false);
    m_borderWidth = 2;
}

void ImageWithBorder::paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget)
{
    painter->save();
    if (!m_source.isEmpty()) {
        QImage image(m_source.toLocalFile());

        if (!image.isNull()) {
            image = image.scaled(this->width(), this->height(), Qt::KeepAspectRatio, Qt::SmoothTransformation);
            QPen pen;
            pen.setColor(Qt::black);
            pen.setWidth(m_borderWidth);
            painter->setPen(pen);
            painter->drawRect(QRect((option->rect.width() - image.width()) / 2 - m_borderWidth,
                                    (option->rect.height() - image.height()) / 2 - m_borderWidth,
                                    image.width() + m_borderWidth + 1, image.height() + m_borderWidth + 1));
            painter->drawImage(QRect((option->rect.width() - image.width()) / 2,
                                     (option->rect.height() - image.height()) / 2,
                                     image.width(), image.height()), image);
        }

    }
    painter->restore();
}

QUrl ImageWithBorder::source() const
{
    return m_source;
}

void ImageWithBorder::setSource(const QUrl &url)
{
    if (m_source != url) {
        m_source = url;
        emit sourceChanged();
        update();
    }
}

int ImageWithBorder::borderWidth()
{
    return m_borderWidth;
}

void ImageWithBorder::setBorderWidth(int width)
{
    if (m_borderWidth != width) {
        m_borderWidth = width;
        emit borderWidthChanged();
        update();
    }
}
