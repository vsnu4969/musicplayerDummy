// IMusicService.aidl
package com.example.aidllibrary;

import com.example.aidllibrary.SongGS;
// Declare any non-default types here with import statements

interface IMusicService {
void play(in SongGS obj);
void next(in SongGS obj);
void previous(in SongGS obj);
}
