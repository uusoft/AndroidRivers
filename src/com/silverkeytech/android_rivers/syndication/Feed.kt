/*
Android Rivers is an app to read and discover news using RiverJs, RSS and OPML format.
Copyright (C) 2012 Dody Gunawinata (dodyg@silverkeytech.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package com.silverkeytech.android_rivers.syndication

import com.silverkeytech.android_rivers.syndications.rss.Rss
import com.silverkeytech.android_rivers.syndications.rss.Item
import java.util.ArrayList

public enum class FeedType{
    NONE
    ATOM
    RSS
}

public data class Feed(public val rss : Rss?, public val atom : Rss?){
    {
        transformRss()
    }

    public var title : String = ""
    public var language : String = ""
    public var feedType : FeedType = FeedType.NONE
    public var items : ArrayList<FeedItem> = ArrayList<FeedItem>()


    public fun transformRss()
    {
        if (rss != null){
            title = if (rss!!.channel!!.title == null) "" else rss!!.channel!!.title!!
            language = if (rss!!.channel!!.language == null) "" else rss!!.channel!!.language!!
            feedType = FeedType.RSS

            for(val i in rss!!.channel!!.item!!.iterator()){
                var fi = FeedItem()
                fi.title = i.title
                fi.description = i.description
                fi.pubDate = i.getPubDate()
                items.add(fi)
            }
        }
    }

    fun transformAtom(){

    }
}