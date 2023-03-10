import { Player } from '/KOF(done)/static/js/players/base.js';
import { GIF } from '/KOF(done)/static/js/utils/gif.js';

class kyo extends Player{
    constructor(root, info) {
        super(root, info);

        this.init_animations();
    }

    init_animations() {
        let outer = this;
        let offsets = [0, -22, -22, -140, 0, 0, 0];//偏移量y
        for (let i = 0; i < 7; i++){
            let gif = GIF();
            gif.load(`/KOF(done)/static/images/player/kyo/${i}.gif`);
            this.animations.set(i, {
                gif: gif,
                frame_cnt: 0,  //总图片数
                frame_rate: 5, //每5帧过度一次
                offset_y: offsets[i],  //y方向偏移量
                loaded: false, //是否加载完成 
                scale:2,  //放大多少倍
            });

            gif.onload = function () {
                let obj = outer.animations.get(i);
                obj.frame_cnt = gif.frames.length;
                obj.loaded = true;

                if (i === 3) {
                    obj.frame_rate = 4;
                }
            }
        }
    }
}

export {
    kyo
}