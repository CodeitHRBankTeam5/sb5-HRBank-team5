package com.codeit.HRBank.controller;

import com.codeit.HRBank.service.BackupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "데이터 백업 관리", description = "데이터 백업 관리 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/backups")
public class BackupController {

    private final BackupService backupService;

    @Operation(summary = "데이터 백업 목록 조회")
    @GetMapping
    public ResponseEntity<List<BackupResponse>> backupList() {
        return ResponseEntity.ok(backupService.findAll());
    }

    @Operation(summary = "데이터 백업 생성")
    @PostMapping
    public ResponseEntity<BackupResponse> create(@RequestBody BackupCreateRequest req) {
        BackupResponse res = backupService.create(req);
        return ResponseEntity.created(URI.create("/api/backups/" + res.id())).body(res);
    }

    @Operation(summary = "최근 백업 정보 조회")
    @GetMapping("/latest")
    public ResponseEntity<BackupResponse> latestBackup() {
        BackupResponse res = backupService.findLatest();
        return (res == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(res);
    }
}
