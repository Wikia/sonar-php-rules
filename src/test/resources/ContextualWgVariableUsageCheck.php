<?php

class FooBar extends WikiaObject {
    public function test() {
        global
            $wgTitle, // NOK
            $wgRequest, // NOK
            $wgUser, // NOK
            $wgOut, // NOK
            $wgArticle; // NOK

        $app = F::app();
        $app->wg->Out->addHTML('test'); // NOK

        $this->wg->Out->addHTML('test2'); // NOK

        $wg = F::app()->wg;
        $wg->Out->addHTML('test3'); // NOK
    }
}