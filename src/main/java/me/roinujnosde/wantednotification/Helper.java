/*
 * The MIT License
 *
 * Copyright 2018 Edson Passos <edsonpassosjr@outlook.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package me.roinujnosde.wantednotification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Edson Passos <edsonpassosjr@outlook.com>
 */
public class Helper {

    public static List<String> uuidListToStringList(List<UUID> uuidList) {
        if (uuidList == null) {
            return null;
        }
        List<String> stringList = new ArrayList<>();
        uuidList.forEach(uuid -> stringList.add(uuid.toString()));
        return stringList;
    }

    public static List<UUID> stringListToUUIDList(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        List<UUID> uuidList = new ArrayList<>();
        stringList.forEach(str -> uuidList.add(UUID.fromString(str))
        );
        return uuidList;
    }

}
