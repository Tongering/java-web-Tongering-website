import { GameMap } from '/KOF(done)/static/js/game_map/base.js'
import {Player} from '/KOF(done)/static/js/players/base.js'
import {kyo} from '/KOF(done)/static/js/players/kyo.js'

class KOF{
    constructor(id) {
        this.$kof = $('#' + id);
        
        this.game_map = new GameMap(this);

        this.players = [
            new kyo(this, {
                id: 0,
                x: 200,
                y: 0,
                width: 120,
                height: 200,
                color:'blue',
            }),
            new kyo(this, {
                id: 1,
                x: 900,
                y: 0,
                width: 120,
                height: 200,
               color:'red',
            }),
        ]
    }
}

export {
    KOF
}